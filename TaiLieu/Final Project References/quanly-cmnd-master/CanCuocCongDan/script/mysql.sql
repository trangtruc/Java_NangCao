
CREATE  TABLE users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));

CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));

INSERT INTO users(username,password,enabled)
VALUES ('mkyong','123456', true);
INSERT INTO users(username,password,enabled)
VALUES ('alex','123456', true);

INSERT INTO user_roles (username, role)
VALUES ('mkyong', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('mkyong', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('alex', 'ROLE_USER');

THONG KE DAN SO CUA TUNG TINH THANH
SELECT T.MA_TINH, T.TEN_TINH, COUNT( * )
FROM KHAI_SINH K, XA X, HUYEN H, TINH T
WHERE QUE_QUAN = X.MA_XA
AND X.MA_HUYEN = H.MA_HUYEN
AND H.MA_TINH = T.MA_TINH
GROUP BY T.MA_TINH
ORDER BY T.MA_TINH ASC
LIMIT 0 , 64

//Insert vo chi_tiet_ho_khau
insert into chi_tiet_ho_khau(stt, so_hk, so_ks, quan_he) 
SELECT c.stt, c.so_hk, k.so_ks, k.quan_he_voi_nguoi_ks
FROM khai_sinh k, chi_tiet_ho_khau c
WHERE k.so_cc_nguoi_yeu_cau = c.so_cc
AND k.so_ks != c.so_ks
AND c.so_cc !=  '000000000000'
AND k.quan_he_voi_nguoi_ks NOT LIKE  '%con%'
LIMIT 0 , 30

//THONG KE DAN SO VUNG
CREATE OR REPLACE VIEW DAN_SO_VUNG AS 
SELECT V.MA_VUNG, V.TEN_VUNG, COUNT( * ) AS DAN_SO
FROM KHAI_SINH K, XA X, HUYEN H, TINH T, VUNG V
WHERE NOI_CAP = X.MA_XA
AND X.MA_HUYEN = H.MA_HUYEN
AND H.MA_TINH = T.MA_TINH
AND T.MA_VUNG = V.MA_VUNG
AND SUBSTRING(NGAY_SINH, 7, 10) < nam
GROUP BY V.MA_VUNG
ORDER BY V.MA_VUNG ASC 
LIMIT 0 , 10;
//
SELECT * 
FROM ttdk_khai_sinh k
WHERE NOT 
EXISTS (
SELECT * 
FROM duyet_dkks
WHERE nguoi_duyet =  '092040000401'
)
and NOT 
EXISTS (
SELECT * 
FROM duyet_dkks d
WHERE d.so_dk =  k.so_dk
)
AND k.so_dk =14
LIMIT 0 , 30

