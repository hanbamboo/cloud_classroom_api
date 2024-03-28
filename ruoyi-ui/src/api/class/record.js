import request from '@/utils/request'

// 查询班级表列表
export function listRecord(query) {
  return request({
    url: '/class/record/list',
    method: 'get',
    params: query
  })
}

// 查询班级表详细
export function getRecord(id) {
  return request({
    url: '/class/record/' + id,
    method: 'get'
  })
}

// 新增班级表
export function addRecord(data) {
  return request({
    url: '/class/record',
    method: 'post',
    data: data
  })
}

// 修改班级表
export function updateRecord(data) {
  return request({
    url: '/class/record',
    method: 'put',
    data: data
  })
}

// 删除班级表
export function delRecord(id) {
  return request({
    url: '/class/record/' + id,
    method: 'delete'
  })
}
