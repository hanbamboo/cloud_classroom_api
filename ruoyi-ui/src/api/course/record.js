import request from '@/utils/request'

// 查询学生拥有的课程列表
export function listRecord(query) {
  return request({
    url: '/course/record/list',
    method: 'get',
    params: query
  })
}

// 查询学生拥有的课程详细
export function getRecord(id) {
  return request({
    url: '/course/record/' + id,
    method: 'get'
  })
}

// 新增学生拥有的课程
export function addRecord(data) {
  return request({
    url: '/course/record',
    method: 'post',
    data: data
  })
}

// 修改学生拥有的课程
export function updateRecord(data) {
  return request({
    url: '/course/record',
    method: 'put',
    data: data
  })
}

// 删除学生拥有的课程
export function delRecord(id) {
  return request({
    url: '/course/record/' + id,
    method: 'delete'
  })
}
