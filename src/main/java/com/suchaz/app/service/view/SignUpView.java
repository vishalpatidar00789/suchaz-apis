package com.suchaz.app.service.view;

import javax.validation.constraints.NotNull;

public class SignUpView {

		@NotNull
	    private String email;

	    @NotNull
	    private String password;
	    
	    @NotNull
	    private String fullName;
	    

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
	
	
}
