package com.dunnas.desafio.components.user.web.mappers;

import org.springframework.stereotype.Component;

import com.dunnas.desafio.components.user.application.usecases.inputs.CreateUserInput;
import com.dunnas.desafio.components.user.application.usecases.outputs.CreateUserOutput;
import com.dunnas.desafio.components.user.web.dtos.CreateUserDto;
import com.dunnas.desafio.components.user.web.dtos.ReadUserDto;

@Component
public class UserDtoMapper {

    public CreateUserInput createUserDtoToCreateUserInput(CreateUserDto dto) {
        if (dto == null) {
            return null;
        }

        return new CreateUserInput(
                dto.getUserName(),
                dto.getPassword(),
                dto.getRole()
        );
    }
    
    public ReadUserDto createUserOutputToReadUserDto(CreateUserOutput output) {
    	if (output == null) {
    		return null;
    	}
    	
    	return new ReadUserDto(
    			output.id(),
    			output.userName(),
    			output.role()
    			);
    }
}
