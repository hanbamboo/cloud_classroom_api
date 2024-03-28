import request from '@/utils/request'

// 查询审批人管理列表
export function listApprover(query) {
  return request({
    url: '/approver/approver/list',
    method: 'get',
    params: query
  })
}

// 查询审批人管理详细
export function getApprover(id) {
  return request({
    url: '/approver/approver/' + id,
    method: 'get'
  })
}

// 新增审批人管理
export function addApprover(data) {
  return request({
    url: '/approver/approver',
    method: 'post',
    data: data
  })
}

// 修改审批人管理
export function updateApprover(data) {
  return request({
    url: '/approver/approver',
    method: 'put',
    data: data
  })
}

// 删除审批人管理
export function delApprover(id) {
  return request({
    url: '/approver/approver/' + id,
    method: 'delete'
  })
}
