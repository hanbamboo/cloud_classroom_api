import request from '@/utils/request'

// 查询学生请假信息列表
export function listLeave(query) {
  return request({
    url: '/leaveApplication/leave/list',
    method: 'get',
    params: query
  })
}

// 查询学生请假信息详细
export function getLeave(id) {
  return request({
    url: '/leaveApplication/leave/' + id,
    method: 'get'
  })
}

// 新增学生请假信息
export function addLeave(data) {
  return request({
    url: '/leaveApplication/leave',
    method: 'post',
    data: data
  })
}

// 修改学生请假信息
export function updateLeave(data) {
  return request({
    url: '/leaveApplication/leave',
    method: 'put',
    data: data
  })
}

// 删除学生请假信息
export function delLeave(id) {
  return request({
    url: '/leaveApplication/leave/' + id,
    method: 'delete'
  })
}
