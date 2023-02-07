package camping.reserve.controller;

import java.util.List;

import camping.reserve.model.dao.ReserveDAO;
import camping.reserve.model.vo.Reserve;

public class ReserveController {
	ReserveDAO reserveDao = new ReserveDAO();

	public int insertReserve(Reserve reserve) {
		return reserveDao.insert(reserve);
	}

	public List<Reserve> selectByUserNo(int userNo) {
		return reserveDao.selectByUserNo(userNo);
	}

	public List<Reserve> selectByReserveNo(int reserveNo) {
		return reserveDao.selectByReserveNo(reserveNo);
	}

	public int deleteReserve(int reserveNo) {
		return reserveDao.delete(reserveNo);
	}
}