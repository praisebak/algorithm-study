-- 코드를 입력하세요
#member id group화 해서 count한다음에 그럼 

SELECT p1.MEMBER_NAME,r.REVIEW_TEXT,DATE_FORMAT(r.REVIEW_DATE,"%Y-%m-%d") AS REVIEW_DATE
FROM MEMBER_PROFILE p1
JOIN REST_REVIEW r ON r.member_id = p1.member_id
WHERE p1.MEMBER_ID = 
    (SELECT member_id
    FROM REST_REVIEW
    GROUP BY(member_id)
    ORDER BY COUNT(member_id) DESC LIMIT 1) 
ORDER BY review_date,review_text