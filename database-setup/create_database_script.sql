CREATE TABLE topic
(
    topic_id SERIAL PRIMARY KEY,
    topic_name TEXT,
    topic_position INTEGER,
    is_deleted BOOLEAN DEFAULT FALSE,
    CONSTRAINT unique_topic_name UNIQUE (topic_name)
);

CREATE TABLE subtopic
(
    st_id SERIAL PRIMARY KEY,
    t_id INTEGER,
    st_position INTEGER,
    st_name TEXT,
    is_deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (t_id) REFERENCE topic (topic_id)
);

CREATE TABLE content (
    c_id SERIAL PRIMARY KEY,
    st_id INTEGER,
    c_position INTEGER NOT NULL,
    c_type content_type NOT NULL,
    content TEXT,
    is_deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (st_id) REFERENCES subtopic (st_id)
);


CREATE TABLE listed_content
(
    lc_id SERIAL PRIMARY KEY,
    c_id INTEGER,
    lc_position INTEGER,
    lc_content TEXT,
    is_deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (c_id) REFERENCES content (c_id)
);