-- hm-branch 데이터베이스 테이블 생성문 (PostgreSQL)
-- 참고용 덤프 파일이며 spring.sql.init에는 연결되어 있지 않습니다.
-- MySQL(hm_branch) 라이브 스키마를 PostgreSQL 문법으로 변환한 것입니다.

DROP TABLE IF EXISTS post_comment;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS notice;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS upload_file;
DROP TABLE IF EXISTS admin_ip_acl;
DROP TABLE IF EXISTS admin_ip_policy;

CREATE TABLE member (
  member_id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  login_id varchar(50) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  mem_nm varchar(50) NOT NULL,
  nick_nm varchar(50) DEFAULT NULL,
  mem_type varchar(5) DEFAULT NULL,
  join_dt timestamp DEFAULT NULL,
  phone_no varchar(20) DEFAULT NULL,
  addr varchar(50) DEFAULT NULL,
  addr_detail varchar(100) DEFAULT NULL,
  del_yn char(1) DEFAULT NULL,
  reg_dt timestamp DEFAULT NULL,
  reg_id varchar(20) DEFAULT NULL,
  reg_ip varchar(100) DEFAULT NULL,
  mod_dt timestamp DEFAULT NULL,
  mod_id varchar(20) DEFAULT NULL,
  mod_ip varchar(100) DEFAULT NULL,
  role_cd varchar(10) NOT NULL DEFAULT 'USER'
);
COMMENT ON COLUMN member.member_id IS '회원 일련번호';
COMMENT ON COLUMN member.login_id IS '로그인 아이디';
COMMENT ON COLUMN member.mem_nm IS '이름';
COMMENT ON COLUMN member.nick_nm IS '닉네임';
COMMENT ON COLUMN member.mem_type IS '코드';
COMMENT ON COLUMN member.join_dt IS '가입 일시';
COMMENT ON COLUMN member.phone_no IS '핸드폰 번호';
COMMENT ON COLUMN member.addr IS '주소';
COMMENT ON COLUMN member.addr_detail IS '상세 주소';
COMMENT ON COLUMN member.del_yn IS '삭제 여부';
COMMENT ON COLUMN member.reg_dt IS '등록 일시';
COMMENT ON COLUMN member.reg_id IS '등록자 ID';
COMMENT ON COLUMN member.reg_ip IS '등록자 IP';
COMMENT ON COLUMN member.mod_dt IS '수정 일시';
COMMENT ON COLUMN member.mod_id IS '수정자 ID';
COMMENT ON COLUMN member.mod_ip IS '수정자 IP';

CREATE TABLE menu (
  menu_id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  menu_nm varchar(50) NOT NULL,
  menu_key varchar(30) NOT NULL,
  menu_type varchar(10) NOT NULL,
  url varchar(500) NOT NULL,
  sort_order int NOT NULL,
  required_role varchar(20) DEFAULT NULL
);

CREATE TABLE notice (
  notice_id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  upload_file_id bigint DEFAULT NULL,
  title varchar(255) NOT NULL,
  content text,
  status_cd varchar(5) DEFAULT NULL,
  view_cnt int DEFAULT NULL,
  like_cnt int DEFAULT NULL,
  important_yn char(1) DEFAULT NULL,
  del_yn char(1) DEFAULT 'N',
  reg_dt timestamp DEFAULT NULL,
  reg_id varchar(20) DEFAULT NULL,
  reg_ip varchar(100) DEFAULT NULL,
  mod_dt timestamp DEFAULT NULL,
  mod_id varchar(20) DEFAULT NULL,
  mod_ip varchar(100) DEFAULT NULL
);
COMMENT ON COLUMN notice.notice_id IS '공지사항 일련번호';
COMMENT ON COLUMN notice.upload_file_id IS '첨부파일 일련번호';
COMMENT ON COLUMN notice.title IS '공지사항 제목';
COMMENT ON COLUMN notice.content IS '공지사항 내용';
COMMENT ON COLUMN notice.status_cd IS '상태 코드';
COMMENT ON COLUMN notice.view_cnt IS '조회 수';
COMMENT ON COLUMN notice.like_cnt IS '좋아요 수';
COMMENT ON COLUMN notice.important_yn IS '중요 여부';
COMMENT ON COLUMN notice.del_yn IS '삭제 여부';
COMMENT ON COLUMN notice.reg_dt IS '등록 일시';
COMMENT ON COLUMN notice.reg_id IS '등록자 ID';
COMMENT ON COLUMN notice.reg_ip IS '등록자 IP';
COMMENT ON COLUMN notice.mod_dt IS '수정 일시';
COMMENT ON COLUMN notice.mod_id IS '수정자 ID';
COMMENT ON COLUMN notice.mod_ip IS '수정자 IP';

CREATE TABLE post (
  article_id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  group_cd varchar(5) DEFAULT NULL,
  detail_cd varchar(5) DEFAULT NULL,
  title varchar(255) NOT NULL,
  content text,
  status_cd varchar(5) DEFAULT NULL,
  view_cnt int DEFAULT '0',
  like_cnt int NOT NULL DEFAULT '0',
  upload_file_id bigint DEFAULT NULL,
  del_yn char(1) NOT NULL DEFAULT 'N',
  reg_dt timestamp DEFAULT NULL,
  reg_id varchar(20) DEFAULT NULL,
  reg_ip varchar(100) DEFAULT NULL,
  mod_dt timestamp DEFAULT NULL,
  mod_id varchar(20) DEFAULT NULL,
  mod_ip varchar(100) DEFAULT NULL
);
COMMENT ON COLUMN post.article_id IS '게시물 일련번호';
COMMENT ON COLUMN post.group_cd IS '그룹 코드';
COMMENT ON COLUMN post.detail_cd IS '상세 코드';
COMMENT ON COLUMN post.title IS '게시물 제목';
COMMENT ON COLUMN post.content IS '게시물 내용';
COMMENT ON COLUMN post.status_cd IS '상태 코드';
COMMENT ON COLUMN post.view_cnt IS '조회수';
COMMENT ON COLUMN post.upload_file_id IS '첨부파일 일련번호';
COMMENT ON COLUMN post.del_yn IS '삭제 여부';
COMMENT ON COLUMN post.reg_dt IS '등록 일시';
COMMENT ON COLUMN post.reg_id IS '등록자 ID';
COMMENT ON COLUMN post.reg_ip IS '등록자 IP';
COMMENT ON COLUMN post.mod_dt IS '수정 일시';
COMMENT ON COLUMN post.mod_id IS '수정자 ID';
COMMENT ON COLUMN post.mod_ip IS '수정자 IP';

CREATE TABLE post_comment (
  article_comment_id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  article_id bigint NOT NULL,
  title varchar(255) DEFAULT NULL,
  content text,
  status_cd varchar(5) DEFAULT NULL,
  del_yn char(1) NOT NULL DEFAULT 'N',
  reg_dt timestamp DEFAULT NULL,
  reg_id varchar(20) DEFAULT NULL,
  reg_ip varchar(100) DEFAULT NULL,
  mod_dt timestamp DEFAULT NULL,
  mod_id varchar(20) DEFAULT NULL,
  mod_ip varchar(100) DEFAULT NULL,
  parent_comment_id bigint DEFAULT NULL,
  CONSTRAINT post_comment_ibfk_1 FOREIGN KEY (article_id) REFERENCES post (article_id)
);
CREATE INDEX idx_post_comment_article_id ON post_comment (article_id);
COMMENT ON COLUMN post_comment.article_comment_id IS '댓글 일련번호';
COMMENT ON COLUMN post_comment.article_id IS '게시물 일련번호';
COMMENT ON COLUMN post_comment.title IS '댓글 제목';
COMMENT ON COLUMN post_comment.content IS '댓글 내용';
COMMENT ON COLUMN post_comment.status_cd IS '상태 코드';
COMMENT ON COLUMN post_comment.del_yn IS '삭제 여부';
COMMENT ON COLUMN post_comment.reg_dt IS '등록 일시';
COMMENT ON COLUMN post_comment.reg_id IS '등록자 ID';
COMMENT ON COLUMN post_comment.reg_ip IS '등록자 IP';
COMMENT ON COLUMN post_comment.mod_dt IS '수정 일시';
COMMENT ON COLUMN post_comment.mod_id IS '수정자 ID';
COMMENT ON COLUMN post_comment.mod_ip IS '수정자 IP';

CREATE TABLE upload_file (
  upload_file_id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  target_type varchar(20) NOT NULL,
  target_id bigint NOT NULL,
  org_file_nm varchar(255) NOT NULL,
  real_file_nm varchar(255) NOT NULL,
  file_size bigint DEFAULT '0',
  del_yn char(1) NOT NULL DEFAULT 'N',
  reg_dt timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_target ON upload_file (target_type, target_id, del_yn);
COMMENT ON TABLE upload_file IS '공통 업로드 파일 관리';
COMMENT ON COLUMN upload_file.upload_file_id IS '파일 PK';
COMMENT ON COLUMN upload_file.target_type IS '연결 도메인 구분 (POST, NOTICE, MEMBER 등)';
COMMENT ON COLUMN upload_file.target_id IS '연결 도메인의 PK 값';
COMMENT ON COLUMN upload_file.org_file_nm IS '원본 파일명';
COMMENT ON COLUMN upload_file.real_file_nm IS '저장된 파일명/경로';
COMMENT ON COLUMN upload_file.file_size IS '파일 크기';
COMMENT ON COLUMN upload_file.del_yn IS '삭제 여부 (Y/N)';
COMMENT ON COLUMN upload_file.reg_dt IS '등록일시';

CREATE TABLE admin_ip_acl (
  acl_id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  ip_addr varchar(50) NOT NULL,
  list_type varchar(10) NOT NULL,
  description varchar(200) DEFAULT NULL,
  use_yn char(1) NOT NULL DEFAULT 'Y',
  reg_dt timestamp DEFAULT NULL,
  reg_id varchar(20) DEFAULT NULL,
  reg_ip varchar(100) DEFAULT NULL,
  mod_dt timestamp DEFAULT NULL,
  mod_id varchar(20) DEFAULT NULL,
  mod_ip varchar(100) DEFAULT NULL
);
COMMENT ON TABLE admin_ip_acl IS '관리자 IP 접근 제어 목록';
COMMENT ON COLUMN admin_ip_acl.acl_id IS 'IP 등록 일련번호';
COMMENT ON COLUMN admin_ip_acl.ip_addr IS 'IP 주소';
COMMENT ON COLUMN admin_ip_acl.list_type IS '목록 유형 (WHITELIST/BLACKLIST)';
COMMENT ON COLUMN admin_ip_acl.description IS '설명';
COMMENT ON COLUMN admin_ip_acl.use_yn IS '사용 여부';
COMMENT ON COLUMN admin_ip_acl.reg_dt IS '등록 일시';
COMMENT ON COLUMN admin_ip_acl.reg_id IS '등록자 ID';
COMMENT ON COLUMN admin_ip_acl.reg_ip IS '등록자 IP';
COMMENT ON COLUMN admin_ip_acl.mod_dt IS '수정 일시';
COMMENT ON COLUMN admin_ip_acl.mod_id IS '수정자 ID';
COMMENT ON COLUMN admin_ip_acl.mod_ip IS '수정자 IP';

CREATE TABLE admin_ip_policy (
  policy_id smallint PRIMARY KEY DEFAULT 1,
  policy_mode varchar(10) NOT NULL DEFAULT 'BLACKLIST',
  mod_dt timestamp DEFAULT NULL,
  mod_id varchar(20) DEFAULT NULL
);
COMMENT ON TABLE admin_ip_policy IS '관리자 IP 접근 정책(단일 행)';
COMMENT ON COLUMN admin_ip_policy.policy_mode IS '접근 정책 모드 (WHITELIST/BLACKLIST), 기본값 BLACKLIST';
COMMENT ON COLUMN admin_ip_policy.mod_dt IS '수정 일시';
COMMENT ON COLUMN admin_ip_policy.mod_id IS '수정자 ID';

INSERT INTO admin_ip_policy (policy_id, policy_mode) VALUES (1, 'BLACKLIST');
