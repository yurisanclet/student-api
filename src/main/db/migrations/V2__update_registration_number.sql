ALTER TABLE student
    ALTER COLUMN registration_number TYPE text;

ALTER TABLE student
    ALTER COLUMN registration_number TYPE bigint
        USING registration_number::bigint;
