queryParentMenuListByUserType
===
    SELECT a.menu_id, menu_name, menu_path, menu_auth, parent_id, menu_status, menu_desc, create_time, update_time 
    FROM manage_menu a 
    WHERE a.user_type = #userType# and a.parent_id is null
    
queryMenuByParentId
====    
    SELECT a.menu_id, menu_name, menu_path, menu_auth, parent_id, menu_status, menu_desc, create_time, update_time 
    FROM manage_menu a 
    WHERE a.parent_id=#parentId# and a.user_type = #userType#


