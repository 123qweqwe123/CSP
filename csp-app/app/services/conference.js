import request from '../utils/request'

export const conferences = async (params) => {
  return request({
    url: '/conferences',
    method: 'get',
    data: params,
  })
}

export const checkins = async (params) => {
  return request({
    url: '/conferences/checkin',
    method: 'get',
    data: params,
  })
}