-- Users table
CREATE TABLE user_app
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username   VARCHAR(50) UNIQUE NOT NULL,
    email      VARCHAR(100) UNIQUE NOT NULL,
    password   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Task table
CREATE SEQUENCE task_id_seq;
CREATE TABLE task
(
    id           BIGINT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('task_id_seq'),
    user_app_id  UUID NOT NULL,
    description  VARCHAR(255) NOT NULL,
    status       VARCHAR(100) NOT NULL,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_task_user_app FOREIGN KEY (user_app_id) REFERENCES user_app(id) ON DELETE CASCADE
);
ALTER SEQUENCE task_id_seq OWNED BY task.id;
