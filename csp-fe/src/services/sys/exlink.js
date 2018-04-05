import { request, config } from 'utils'

const { api } = config
const { sys } = api
const { exlinks } = sys

export async function queryExlink (params) {
  return request({
    url: `${exlinks}/:reportId`,
    method: 'get',
    data: params,
  })
}

export async function query (params) {
  return request({
    url: exlinks,
    method: 'get',
    data: params,
  })
}

export async function create (params) {
  return request({
    url: exlinks,
    method: 'post',
    data: params,
  })
}

export async function update (params) {
  return request({
    url: exlinks,
    method: 'patch',
    data: params,
  })
}

export async function remove (params) {
  return request({
    url: `${exlinks}?id=${params.id}`,
    method: 'delete',
    data: params,
  })
}
