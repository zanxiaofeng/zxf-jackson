package zxf.jackson.json.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.regex.Pattern;

@JsonSerialize
public class MyAuthentication implements Authentication {
    private MyUser myUser;

    //Add for json serialize
    public MyAuthentication() {
    }

    public MyAuthentication(MyUser myUser) {
        this.myUser = myUser;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public Object getCredentials() {
        return null;
    }

    @Override
    @JsonIgnore
    public Object getDetails() {
        return null;
    }

    @Override
    @JsonIgnore
    public Object getPrincipal() {
        return myUser;
    }

    @Override
    @JsonIgnore
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    @JsonIgnore
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    @JsonIgnore
    public String getName() {
        return myUser.getName();
    }

    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }

    @JsonSerialize
    public static class MyUser implements Serializable {
        private String name;
        private Integer age = 1;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "UTC")
        private ZonedDateTime createTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("Australia/Sydney"));
        private LocalDateTime updateTime = LocalDateTime.now();

        //Add for json serialize
        public MyUser() {
        }

        public MyUser(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public ZonedDateTime getCreateTime() {
            return createTime;
        }

        public void setCreateTime(ZonedDateTime createTime) {
            this.createTime = createTime;
        }

        public LocalDateTime getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(LocalDateTime updateTime) {
            this.updateTime = updateTime;
        }
    }
}
