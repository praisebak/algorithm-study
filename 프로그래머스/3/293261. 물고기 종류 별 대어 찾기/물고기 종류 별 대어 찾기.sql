SELECT f.ID,f2.FISH_NAME,f.length
FROM FISH_INFO f
LEFT OUTER JOIN FISH_NAME_INFO f2 ON f.fish_type = f2.fish_type
where f.length IN (SELECT MAX(length) FROM fish_info WHERE f.fish_type = fish_type)
ORDER BY id

