package com.muteng.dgjs.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface AddressBookService {
	Object[] synchronizeRelationship(Map<String, Object> paramMap);
	Object[] invitingfriends(Map<String, Object> paramMap) throws UnsupportedEncodingException;
}
