package com.zeeplivework.app.response;

public class LoginResponse {

    String message, error, already_registered;
    boolean success;
    Result result;

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public boolean isSuccess() {
        return success;
    }

    public Result getResult() {
        return result;
    }

    public String getAlready_registered() {
        return already_registered;
    }

    public void setAlready_registered(String already_registered) {
        this.already_registered = already_registered;
    }

    public static class Result {

        String token, name, gender, profile_id, user_city, country;
        int is_online,level;
        int guest_status;
        int allow_in_app_purchase;
        String login_type, username, demo_password ;

        public int getIs_online() {
            return is_online;
        }

        public int getGuest_status() {
            return guest_status;
        }

        public void setGuest_status(int guest_status) {
            this.guest_status = guest_status;
        }

        public String getToken() {
            return token;
        }

        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }

        public String getProfile_id() {
            return profile_id;
        }

        public String getLogin_type() {
            return login_type;
        }

        public String getUsername() {
            return username;
        }

        public String getDemo_password() {
            return demo_password;
        }

        public int getAllow_in_app_purchase() {
            return allow_in_app_purchase;
        }

        public void setAllow_in_app_purchase(int allow_in_app_purchase) {
            this.allow_in_app_purchase = allow_in_app_purchase;
        }

        public String getUser_city() {
            return user_city;
        }

        public void setUser_city(String user_city) {
            this.user_city = user_city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }
}