import request from '@/utils/request'

// 查询已读通知列表
export function listRecord(query) {
  return request({
    url: '/notification/record/list',
    method: 'get',
    params: query
  })
}

// 查询已读通知详细
export function getRecord(id) {
  return request({
    url: '/notification/record/' + id,
    method: 'get'
  })
}

// 新增已读通知
export function addRecord(data) {
  return request({
    url: '/notification/record',
    method: 'post',
    data: data
  })
}

// 修改已读通知
export function updateRecord(data) {
  return request({
    url: '/notification/record',
    method: 'put',
    data: data
  })
}

// 删除已读通知
export function delRecord(id) {
  return request({
    url: '/notification/record/' + id,
    method: 'delete'
  })
}
