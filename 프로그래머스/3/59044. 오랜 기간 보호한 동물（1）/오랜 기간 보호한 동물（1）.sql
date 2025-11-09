-- 코드를 입력하세요
SELECT 
    animal_in.NAME, 
    animal_in.DATETIME
FROM 
    ANIMAL_INS AS animal_in
LEFT OUTER JOIN 
    ANIMAL_OUTS AS animal_out ON animal_in.ANIMAL_ID = animal_out.ANIMAL_ID
WHERE 
    animal_out.ANIMAL_ID IS NULL  -- [핵심] 입양(OUT) 테이블에 없는 동물만 필터링
ORDER BY 
    animal_in.DATETIME ASC           -- 보호 시작일이 빠른 순 (오래된 순)
LIMIT 3;                             -- 3마리만 조회