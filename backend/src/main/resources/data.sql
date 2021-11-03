-- 노트 디자인
insert into note_design values (1, 'design-url-1') ON DUPLICATE KEY UPDATE id=1;

-- 노트 레이아웃
insert into note_layout values (1) ON DUPLICATE KEY UPDATE id=1;

-- 사용자
insert into user values ('test1', 50, '의적', 'https://papers-bucket.s3.amazonaws.com/profile/test1/eb3df4c9-a031-42c9-9bb5-0d3562de7995landing-main.jpg', '$2a$10$9RbUeMd5zBbv1bic5Me.euAyCi1fqlwbWhKfmn0k3D7bnqJCtJhx.') ON DUPLICATE KEY UPDATE user_id='test1';

-- 폰트
insert into font values (1, 'font1', 50, 'font-url-1') ON DUPLICATE KEY UPDATE id=1;
insert into font values (2, 'font2', 60, 'font-url-2') ON DUPLICATE KEY UPDATE id=2;
insert into font values (3, 'font3', 70, 'font-url-3') ON DUPLICATE KEY UPDATE id=3;
insert into font values (4, 'font4', 80, 'font-url-4') ON DUPLICATE KEY UPDATE id=4;

-- 유저 폰트
insert into user_font values (1, 3, 'test1') ON DUPLICATE KEY UPDATE id=1;
insert into user_font values (2, 2, 'test1') ON DUPLICATE KEY UPDATE id=2;
insert into user_font values (3, 3, 'test1') ON DUPLICATE KEY UPDATE id=3;

-- 스티커 패키지
insert into sticker_package values (1, 'package1', 50) ON DUPLICATE KEY UPDATE id=1;

-- 다이어리 커버
insert into diary_cover values (1, 'cover1', 500, 'test.com') ON DUPLICATE KEY UPDATE id=1;

-- 다이어리
insert into diary values (1, sysdate(), 'desc1', 'diary-title-1', 1, 'test1') ON DUPLICATE KEY UPDATE id=1;

-- 노트
insert into note (id, note_content, note_create_date, note_create_time, note_title, diary_id, font_id, design_id, layout_id, writer_id)
 values (1, 'content1', sysdate(), localtime(), 'title1', 1, 1, 1, 1, 'test1') ON DUPLICATE KEY UPDATE id = 1;

-- 스티커
insert into sticker values (1, 'sticker-url-1', 1) ON DUPLICATE KEY UPDATE id=1;
insert into sticker values (2, 'sticker-url-2', 1) ON DUPLICATE KEY UPDATE id=2;
insert into sticker values (3, 'sticker-url-3', 1) ON DUPLICATE KEY UPDATE id=3;

-- 유저 스티커 패키지
insert into user_sticker_package values (1, 1, 'test1') ON DUPLICATE KEY UPDATE id=1;

-- 노트 해시태그
insert into note_hashtag values (1, '좋아요', 1) ON DUPLICATE KEY UPDATE id=1;

-- 노트 미디어
insert into note_media values (1, '.jpg', 'media-url-1', 1) ON DUPLICATE KEY UPDATE id=1;

-- 다이어리 커버
insert into diary_cover values (1, '다이어리 커버1', 600, 'img url-1') ON DUPLICATE KEY UPDATE id=1;
insert into diary_cover values (2, '다이어리 커버2', 600, 'img url-2') ON DUPLICATE KEY UPDATE id=2;
insert into diary_cover values (3, '다이어리 커버3', 600, 'img url-3') ON DUPLICATE KEY UPDATE id=3;

-- 유저 다이어리 커버
insert into user_diary_cover values (1, 1, 'test1') ON DUPLICATE KEY UPDATE id=1;
insert into user_diary_cover values (2, 2, 'test1') ON DUPLICATE KEY UPDATE id=2;

-- 노티피케이션 정보
insert into notification_info values (1, '일기 작성 알림') ON DUPLICATE KEY UPDATE id=1;
insert into notification_info values (2, '댓글 알림') ON DUPLICATE KEY UPDATE id=2;
insert into notification_info values (3, '초대 알림') ON DUPLICATE KEY UPDATE id=3;

-- 노티피케이션
insert into notification values (1, 'giver님이 일기를 작성했습니다.', '2021-11-03', 0, '11:11:11', '타입', 1, 'test1') ON DUPLICATE KEY UPDATE id=1;
insert into notification values (2, 'giver님이 댓글 작성했습니다.', '2021-11-03', 0, '11:11:11', '타입', 2, 'test1') ON DUPLICATE KEY UPDATE id=2;