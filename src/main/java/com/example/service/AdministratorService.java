package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

/**
 * 管理者情報を操作するサービス.
 * 
 * @author igamasayuki
 *
 */
@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository;

	/**
	 * 管理者情報を登録します.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {
		// メールアドレスがすでに存在するかを確認
        Administrator existingAdministrator = administratorRepository.findByMailAddress(administrator.getMailAddress());
		// administratorRepository.insert(administrator);
		// 存在する場合はエラーメッセージを返す
        if (existingAdministrator != null) {
            throw new IllegalArgumentException("メールアドレスが重複しています");
        }
		// 存在しない場合、登録処理
        administratorRepository.save(administrator);
    }
	    /**
     * メールアドレスが既に存在するかを確認するメソッド
     */
    public boolean ismailAdressExist(String email) {
        Administrator existingAdministrator = administratorRepository.findByMailAddress(email);
        return existingAdministrator != null;
    }

	/**
	 * ログインをします.
	 * 
	 * @param mailAddress メールアドレス
	 * @param password    パスワード
	 * @return 管理者情報 存在しない場合はnullが返ります
	 */
	public Administrator login(String mailAddress, String password) {
		Administrator administrator = administratorRepository.findByMailAddressAndPassward(mailAddress, password);
		return administrator;
	}
}
