import { request, config } from '../utils'

const { api } = config
const { dashboard } = api

export async function query (params) {
  return request({
    url: dashboard,
    method: 'get',
    data: params,
  })
}

export async function queryUserDashboards (params) {
  return request({
    url: `${dashboard}/user`,
    method: 'get',
    data: params,
  })
}

export async function save (params) {
  return request({
    url: dashboard,
    method: 'post',
    data: params,
  })
}
