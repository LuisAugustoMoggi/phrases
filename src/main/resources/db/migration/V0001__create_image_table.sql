CREATE TABLE T_IMAGES (
    IMAGE_ID BIGSERIAL PRIMARY KEY,
    IMAGE_URL VARCHAR(500) NOT NULL UNIQUE
);