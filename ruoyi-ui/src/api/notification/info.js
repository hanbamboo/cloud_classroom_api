import request from '@/utils/request'

// 查询课堂通知列表
export function listInfo(query) {
  return request({
    url: '/notification/info/list',
    method: 'get',
    params: query
  })
}

// 查询课堂通知详细
export function getInfo(id) {
  return request({
    url: '/notification/info/' + id,
    method: 'get'
  })
}

// 新增课堂通知
export function addInfo(data) {
  return request({
    url: '/notification/info',
    method: 'post',
    data: data
  })
}

// 修改课堂通知
export function updateInfo(data) {
  return request({
    url: '/notification/info',
    method: 'put',
    data: data
  })
}

// 删除课堂通知
export function delInfo(id) {
  return request({
    url: '/notification/info/' + id,
    method: 'delete'
  })
}
