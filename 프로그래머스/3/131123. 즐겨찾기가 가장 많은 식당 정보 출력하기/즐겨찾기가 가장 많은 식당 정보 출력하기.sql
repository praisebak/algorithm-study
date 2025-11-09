SELECT food_type as FOOD_TYPE,rest_id as REST_ID,rest_name as REST_NAME, r.favorites as FAVORITES
FROM rest_info as r
WHERE (food_type,favorites) IN (SELECT food_type,MAX(favorites) FROM rest_info GROUP BY
            food_type)
ORDER BY
    food_type DESC;