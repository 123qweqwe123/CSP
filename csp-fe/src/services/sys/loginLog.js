import { request, config } from '../../utils'

const { api } = config
const { sys } = api
const { loginLog } = sys

export async function query (params) {
  return request({
    url: loginLog,
    method: 'get',
    data: params,
  })
}

