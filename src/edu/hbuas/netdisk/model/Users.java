package edu.hbuas.netdisk.model;

import java.io.Serializable;

/**
 * 网盘用户信息表 儌僨儖僋儔僗.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class Users implements Serializable {

	@Override
	public String toString() {
		return "Users [username=" + username + ", password=" + password + ", sex=" + sex + ", age=" + age + ", email="
				+ email + ", tel=" + tel + "]";
	}

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 账户名. */
	private String username;

	/** 网盘密码. */
	private String password;

	/** 用户性别. */
	private String sex;

	/** 用户年龄. */
	private Integer age;

	/** 用户邮箱. */
	private String email;

	/** 用户电话. */
	private String tel;

	/**
	 * 僐儞僗僩儔僋僞.
	 */
	public Users() {
	}

	/**
	 * 账户名 傪愝掕偟傑偡.
	 * 
	 * @param username
	 *            账户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 账户名 傪庢摼偟傑偡.
	 * 
	 * @return 账户名
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * 网盘密码 傪愝掕偟傑偡.
	 * 
	 * @param password
	 *            网盘密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 网盘密码 傪庢摼偟傑偡.
	 * 
	 * @return 网盘密码
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * 用户性别 傪愝掕偟傑偡.
	 * 
	 * @param sex
	 *            用户性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 用户性别 傪庢摼偟傑偡.
	 * 
	 * @return 用户性别
	 */
	public String getSex() {
		return this.sex;
	}

	/**
	 * 用户年龄 傪愝掕偟傑偡.
	 * 
	 * @param age
	 *            用户年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * 用户年龄 傪庢摼偟傑偡.
	 * 
	 * @return 用户年龄
	 */
	public Integer getAge() {
		return this.age;
	}

	/**
	 * 用户邮箱 傪愝掕偟傑偡.
	 * 
	 * @param email
	 *            用户邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 用户邮箱 傪庢摼偟傑偡.
	 * 
	 * @return 用户邮箱
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * 用户电话 傪愝掕偟傑偡.
	 * 
	 * @param tel
	 *            用户电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * 用户电话 傪庢摼偟傑偡.
	 * 
	 * @return 用户电话
	 */
	public String getTel() {
		return this.tel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Users other = (Users) obj;
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}

		return true;
	}

}
