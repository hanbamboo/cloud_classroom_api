import request from '@/utils/request'

// 查询课程列表
export function listInfo(query) {
  return request({
    url: '/course/info/list',
    method: 'get',
    params: query
  })
}

// 查询课程详细
export function getInfo(id) {
  return request({
    url: '/course/info/' + id,
    method: 'get'
  })
}

// 新增课程
export function addInfo(data) {
  return request({
    url: '/course/info',
    method: 'post',
    data: data
  })
}

// 修改课程
export function updateInfo(data) {
  return request({
    url: '/course/info',
    method: 'put',
    data: data
  })
}

// 删除课程
export function delInfo(id) {
  return request({
    url: '/course/info/' + id,
    method: 'delete'
  })
}
