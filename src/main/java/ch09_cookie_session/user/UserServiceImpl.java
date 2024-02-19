package ch09_cookie_session.user;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDao();
	
	@Override
	// 매개변수 user는 아직 데이터가 암호화되지 않음
	// 따라서 이 메소드에서 패스워드를 암호화시키고 DB에 입력
	public void registerUser(User user) { 
		User u = userDao.getUserByUid(user.getUid());
		if (u != null)
			return;
		
		// 패스워드 암호화
		String hashedPwd = BCrypt.hashpw(user.getPwd(), BCrypt.gensalt());
		user.setPwd(hashedPwd);
		userDao.insertUser(user);
	}

	@Override
	public User getUserByUid(String uid) {
		User user = userDao.getUserByUid(uid);
		return user;
	}

	@Override
	public List<User> getUserList(int page) {
		int offset = (page - 1) * COUNT_PER_PAGE; // 페이지의 수 * 페이지마다 개수만큼 건너뜀
		List<User> list = userDao.getUserList(COUNT_PER_PAGE, offset);
		
		return list;
	}
	
	public void insertUser(User user) {
		userDao.insertUser(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public void deleteUser(String uid) {
		userDao.deleteUser(uid);
	}

	@Override
	public int login(String uid, String pwd) {
		User user = userDao.getUserByUid(uid);
		
		if (user == null)
			return USER_NOT_EXIST;
		
		if (!BCrypt.checkpw(pwd, user.getPwd()))
			return WRONG_PASSWORD;
		
		return CORRECT_LOGIN;
	}

	
}
