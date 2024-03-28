import request from '@/utils/request'

// 查询签到信息列表
export function listCheckin(query) {
  return request({
    url: '/checkIn/checkin/list',
    method: 'get',
    params: query
  })
}

// 查询签到信息详细
export function getCheckin(id) {
  return request({
    url: '/checkIn/checkin/' + id,
    method: 'get'
  })
}

// 新增签到信息
export function addCheckin(data) {
  return request({
    url: '/checkIn/checkin',
    method: 'post',
    data: data
  })
}

// 修改签到信息
export function updateCheckin(data) {
  return request({
    url: '/checkIn/checkin',
    method: 'put',
    data: data
  })
}

// 删除签到信息
export function delCheckin(id) {
  return request({
    url: '/checkIn/checkin/' + id,
    method: 'delete'
  })
}
