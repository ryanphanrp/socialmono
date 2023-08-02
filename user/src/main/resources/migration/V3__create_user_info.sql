CREATE TABLE IF NOT EXISTS user_infos
(
    user_info_id bigint PRIMARY KEY AUTO_INCREMENT,
    user_id      bigint,
    name         nvarchar(256),
    bio          text(999),
    followers    int,
    following    int,
    num_posts    int
);