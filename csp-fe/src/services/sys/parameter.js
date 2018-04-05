import { request, config } from '../../utils'

const { api } = config
const { sys } = api
const { parameters } = sys

export async function query (params) {
  return request({
    url: parameters,
    method: 'get',
    data: params,
  })
}

export async function create (params) {
  return request({
    url: parameters,
    method: 'post',
    data: params,
  })
}

export async function remove (params) {
  return request({
    url: `${parameters}?id=${params.id}`,
    method: 'delete',
    data: params,
  })
}

export async function update (params) {
  return request({
    url: parameters,
    method: 'patch',
    data: params,
  })
}

export async function queryParameter (params) {
  return request({
    url: `${parameters}/${params.typeCode}`,
    method: 'get',
    data: params,
  })
}

export async function createParameter (params) {
  return request({
    url: `${parameters}/${params.typeCode}`,
    method: 'post',
    data: params,
  })
}

export async function removeParameter (params) {
  return request({
    url: `${parameters}/${params.typeCode}?id=${params.id}`,
    method: 'delete',
    data: params,
  })
}

export async function updateParameter (params) {
  return request({
    url: `${parameters}/${params.typeCode}`,
    method: 'patch',
    data: params,
  })
}

export async function sortParameter (params) {
  return request({
    url: `${parameters}/${params.typeCode}/sort`,
    method: 'patch',
    data: params,
  })
}
