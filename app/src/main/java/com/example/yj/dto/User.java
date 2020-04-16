package com.example.yj.dto;

public class User {

        private int userId;
        private String email;
        private String password;
        private String userType;
        private double aadhar_number;
        private String name;

        private String gender;
        private long mobile;

        private int pId;

        private int age;

        public int getpId() {
            return pId;
        }

        public void setpId(int pId) {
            this.pId = pId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public long getMobile() {
            return mobile;
        }

        public void setMobile(long mobile) {
            this.mobile = mobile;
        }

        public int getUserId() {
            return userId;
        }

        public double getAadhar_number() {
            return aadhar_number;
        }

        public void setAadhar_number(double aadhar_number) {
            this.aadhar_number = aadhar_number;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

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

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }
    }
