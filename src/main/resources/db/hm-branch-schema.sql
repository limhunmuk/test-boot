-- hm-branch 데이터베이스 테이블 생성문 (MySQL)
-- 참고용 덤프 파일이며 spring.sql.init에는 연결되어 있지 않습니다.

DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `article_id` bigint NOT NULL AUTO_INCREMENT COMMENT '게시물 일련번호',
  `group_cd` varchar(5) DEFAULT NULL COMMENT '그룹 코드',
  `detail_cd` varchar(5) DEFAULT NULL COMMENT '상세 코드',
  `title` varchar(255) NOT NULL COMMENT '게시물 제목',
  `content` longtext COMMENT '게시물 내용',
  `status_cd` varchar(5) DEFAULT NULL COMMENT '상태 코드',
  `view_cnt` int DEFAULT '0' COMMENT '조회수',
  `like_cnt` int NOT NULL DEFAULT '0',
  `upload_file_id` bigint DEFAULT NULL COMMENT '첨부파일 일련번호',
  `del_yn` char(1) NOT NULL DEFAULT 'N' COMMENT '삭제 여부',
  `reg_dt` datetime DEFAULT NULL COMMENT '등록 일시',
  `reg_id` varchar(20) DEFAULT NULL COMMENT '등록자 ID',
  `reg_ip` varchar(100) DEFAULT NULL COMMENT '등록자 IP',
  `mod_dt` datetime DEFAULT NULL COMMENT '수정 일시',
  `mod_id` varchar(20) DEFAULT NULL COMMENT '수정자 ID',
  `mod_ip` varchar(100) DEFAULT NULL COMMENT '수정자 IP',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `post_comment`;
CREATE TABLE `post_comment` (
  `article_comment_id` bigint NOT NULL AUTO_INCREMENT COMMENT '댓글 일련번호',
  `article_id` bigint NOT NULL COMMENT '게시물 일련번호',
  `title` varchar(255) DEFAULT NULL COMMENT '댓글 제목',
  `content` longtext COMMENT '댓글 내용',
  `status_cd` varchar(5) DEFAULT NULL COMMENT '상태 코드',
  `del_yn` char(1) NOT NULL DEFAULT 'N' COMMENT '삭제 여부',
  `reg_dt` datetime DEFAULT NULL COMMENT '등록 일시',
  `reg_id` varchar(20) DEFAULT NULL COMMENT '등록자 ID',
  `reg_ip` varchar(100) DEFAULT NULL COMMENT '등록자 IP',
  `mod_dt` datetime DEFAULT NULL COMMENT '수정 일시',
  `mod_id` varchar(20) DEFAULT NULL COMMENT '수정자 ID',
  `mod_ip` varchar(100) DEFAULT NULL COMMENT '수정자 IP',
  PRIMARY KEY (`article_comment_id`),
  KEY `article_id` (`article_id`),
  CONSTRAINT `post_comment_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `post` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `upload_file`;
CREATE TABLE `upload_file` (
  `upload_file_id` bigint NOT NULL AUTO_INCREMENT COMMENT '파일 PK',
  `target_type` varchar(20) NOT NULL COMMENT '연결 도메인 구분 (POST, NOTICE, MEMBER 등)',
  `target_id` bigint NOT NULL COMMENT '연결 도메인의 PK 값',
  `org_file_nm` varchar(255) NOT NULL COMMENT '원본 파일명',
  `real_file_nm` varchar(255) NOT NULL COMMENT '저장된 파일명/경로',
  `file_size` bigint DEFAULT '0' COMMENT '파일 크기',
  `del_yn` char(1) NOT NULL DEFAULT 'N' COMMENT '삭제 여부 (Y/N)',
  `reg_dt` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
  PRIMARY KEY (`upload_file_id`),
  KEY `idx_target` (`target_type`,`target_id`,`del_yn`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='공통 업로드 파일 관리';

DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `notice_id` bigint NOT NULL AUTO_INCREMENT COMMENT '공지사항 일련번호',
  `upload_file_id` bigint DEFAULT NULL COMMENT '첨부파일 일련번호',
  `title` varchar(255) NOT NULL COMMENT '공지사항 제목',
  `content` longtext COMMENT '공지사항 내용',
  `status_cd` varchar(5) DEFAULT NULL COMMENT '상태 코드',
  `view_cnt` int DEFAULT NULL COMMENT '조회 수',
  `like_cnt` int DEFAULT NULL COMMENT '좋아요 수',
  `important_yn` char(1) DEFAULT NULL COMMENT '중요 여부',
  `del_yn` char(1) DEFAULT 'N' COMMENT '삭제 여부',
  `reg_dt` datetime DEFAULT NULL COMMENT '등록 일시',
  `reg_id` varchar(20) DEFAULT NULL COMMENT '등록자 ID',
  `reg_ip` varchar(100) DEFAULT NULL COMMENT '등록자 IP',
  `mod_dt` datetime DEFAULT NULL COMMENT '수정 일시',
  `mod_id` varchar(20) DEFAULT NULL COMMENT '수정자 ID',
  `mod_ip` varchar(100) DEFAULT NULL COMMENT '수정자 IP',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `member_id` bigint NOT NULL AUTO_INCREMENT COMMENT '회원 일련번호',
  `login_id` varchar(50) DEFAULT NULL COMMENT '로그인 아이디',
  `password` varchar(255) DEFAULT NULL,
  `mem_nm` varchar(50) NOT NULL COMMENT '이름',
  `nick_nm` varchar(50) DEFAULT NULL COMMENT '닉네임',
  `mem_type` varchar(5) DEFAULT NULL COMMENT '코드',
  `join_dt` datetime DEFAULT NULL COMMENT '가입 일시',
  `phone_no` varchar(20) DEFAULT NULL COMMENT '핸드폰 번호',
  `addr` varchar(50) DEFAULT NULL COMMENT '주소',
  `addr_detail` varchar(100) DEFAULT NULL COMMENT '상세 주소',
  `del_yn` char(1) DEFAULT NULL COMMENT '삭제 여부',
  `reg_dt` datetime DEFAULT NULL COMMENT '등록 일시',
  `reg_id` varchar(20) DEFAULT NULL COMMENT '등록자 ID',
  `reg_ip` varchar(100) DEFAULT NULL COMMENT '등록자 IP',
  `mod_dt` datetime DEFAULT NULL COMMENT '수정 일시',
  `mod_id` varchar(20) DEFAULT NULL COMMENT '수정자 ID',
  `mod_ip` varchar(100) DEFAULT NULL COMMENT '수정자 IP',
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

