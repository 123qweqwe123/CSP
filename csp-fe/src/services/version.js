import { request, config } from '../utils'

const { api } = config
const { dataversion } = api
//const { roles, permissions } = sys

export default async function query(params) {
  return request({
    url: dataversion,
    method: 'get',
    data: params,
  })
}

export async function update(params) {
  return request({
    url: dataversion,
    method: 'patch',
    data: params,
  })
}

