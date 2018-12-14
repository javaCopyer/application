package com.muteng.dgjs.DTO;

import java.util.List;

import lombok.Data;

import com.muteng.dgjs.domain.User;

@Data
public class ClientsDTO {

	private Integer invitednumber;
	private List<User> inviter;
}
