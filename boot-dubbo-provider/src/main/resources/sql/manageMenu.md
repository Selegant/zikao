queryParentMenuListByRoleId
===
    SELECT a.menu_id, menu_name, menu_path, menu_auth, parent_id, menu_status, menu_desc, create_time, update_time 
    FROM manage_menu a INNER JOIN manage_role_menu b on a.menu_id=b.menu_id
    WHERE b.role_id = #roleId# and a.parent_id is null
    
queryMenuByParentId
====    
    SELECT a.menu_id, menu_name, menu_path, menu_auth, parent_id, menu_status, menu_desc, create_time, update_time 
    FROM manage_menu a INNER JOIN manage_role_menu b on a.menu_id=b.menu_id
    WHERE a.parent_id=#parentId# and b.role_id= #roleId#

queryParentMenuListByRoleIdAndPath
===
    SELECT a.menu_id, menu_name, menu_path, menu_auth, parent_id, menu_status, menu_desc, create_time, update_time 
    FROM manage_menu a INNER JOIN manage_role_menu b on a.menu_id=b.menu_id
    WHERE b.role_id = #roleId# and menu_path = #path#


getMenuListByPage
===
    SELECT #page("d.*")# FROM manage_menu d WHERE 1=1 order by d.CREATE_TIME desc 
