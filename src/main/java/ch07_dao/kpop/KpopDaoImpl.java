package ch07_dao.kpop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class KpopDaoImpl implements KpopDao {
	
	private Connection getConnection() {
		Connection conn = null;
		
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/" + "jdbc/world"); // 링크에 맞는 DataSource 호출
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	@Override
	public List<Kpop> getKpopList() {
		Connection conn = getConnection();
		String sql = "select l.*, r.* from girl_group l"
				+ " left outer join song r"
				+ " on l.hit_song_id=r.sid";
//		String sql2 = "SELECT r.gid, r.name, r.debut, l.sid, l.title, l.lyrics FROM song l"
//				+ "	LEFT OUTER JOIN girl_group r"
//				+ "	ON l.sid=r.hit_song_id"
//				+ "UNION"
//				+ "SELECT r.gid, r.name, r.debut, l.sid, l.title, l.lyrics FROM song l"
//				+ "	RIGHT OUTER JOIN girl_group r"
//				+ "	ON l.sid=r.hit_song_id";
		
		List<Kpop> list = new ArrayList<Kpop>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				Kpop k = new Kpop(rs.getInt(1), rs.getString(2), LocalDate.parse(rs.getString(3)), rs.getInt(4),
						rs.getString(6), rs.getString(7));
				
				if (rs.getString(6) != null)
				{
					k.setTitle(rs.getString(6));
					k.setLyrics(rs.getString(7));
				}
				else
				{
					k.setTitle("데이터 없음");
					k.setLyrics("-");
				}
				
				list.add(k);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public Artist getArtist(int aid) {
		Connection conn = getConnection();
		String sql = "select * from girl_group where gid=?";
		Artist artist = null;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aid);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				artist = new Artist(rs.getInt(1), rs.getString(2), LocalDate.parse(rs.getString(3)), rs.getInt(4));
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return artist;
	}

	@Override
	public Song getSong(int sid) {
		Connection conn = getConnection();
		String sql = "select * from song where sid=?";
		Song song = null;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				song = new Song(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return song;
	}

	@Override
	public void insertArtist(Artist artist) {
		Connection conn = getConnection();
		String sql = "insert into girl_group values (default, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, artist.getName());
			pstmt.setString(2, artist.getDebut().toString());
			pstmt.setInt(3, artist.getHit_song_id());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertSong(Song song) {
		Connection conn = getConnection();
		String sql = "insert into song values (default, ?, ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, song.getTitle());
			pstmt.setString(2, song.getLyrics());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateArtist(Artist artist) {
		Connection conn = getConnection();
		String sql = "update girl_group set name=?, debut=?, hit_song_id=? where gid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, artist.getName());
			pstmt.setString(2, artist.getDebut().toString());
			pstmt.setInt(3, artist.getHit_song_id());
			pstmt.setInt(4, artist.getAid());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateSong(Song song) {
		Connection conn = getConnection();
		String sql = "update song set title=?, Lyrics=? where sid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, song.getTitle());
			pstmt.setString(2, song.getLyrics());
			pstmt.setInt(3, song.getSid());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteArtist(int aid) {
		Connection conn = getConnection();
		String sql = "delete from girl_group where gid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aid);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteSong(int sid) {
		Connection conn = getConnection();
		String sql = "delete from song where sid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
