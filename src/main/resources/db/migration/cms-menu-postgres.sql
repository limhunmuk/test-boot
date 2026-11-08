-- Apply once to the existing Pungdong PostgreSQL database before starting this version.
-- This file is not executed automatically. Existing menu records remain root menus.
BEGIN;
ALTER TABLE menu ADD COLUMN parent_menu_id bigint;
ALTER TABLE menu ADD COLUMN menu_dc varchar(500);
ALTER TABLE menu ADD COLUMN use_yn char(1) NOT NULL DEFAULT 'Y';
ALTER TABLE menu ADD COLUMN public_yn char(1) NOT NULL DEFAULT 'Y';
ALTER TABLE menu ADD COLUMN reg_id varchar(50);
ALTER TABLE menu ADD COLUMN reg_dt timestamp;
ALTER TABLE menu ADD COLUMN mod_id varchar(50);
ALTER TABLE menu ADD COLUMN mod_dt timestamp;
ALTER TABLE menu ADD CONSTRAINT fk_menu_parent FOREIGN KEY (parent_menu_id) REFERENCES menu(menu_id);
ALTER TABLE menu ADD CONSTRAINT ck_menu_parent CHECK (parent_menu_id IS NULL OR parent_menu_id <> menu_id);
ALTER TABLE menu ADD CONSTRAINT ck_menu_use CHECK (use_yn IN ('Y', 'N'));
ALTER TABLE menu ADD CONSTRAINT ck_menu_public CHECK (public_yn IN ('Y', 'N'));
CREATE UNIQUE INDEX uq_menu_key ON menu(menu_key);
CREATE INDEX idx_menu_parent_sort ON menu(parent_menu_id, sort_order, menu_id);
COMMIT;
