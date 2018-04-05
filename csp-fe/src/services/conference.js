import { request, config } from '../utils'

const { api } = config
const { conferences } = api

// 查询会议
export async function query (params) {
  return request({
    url: conferences,
    method: 'get',
    data: params,
  })
}
// 创建会议
export async function create (params) {
  return request({
    url: conferences,
    method: 'post',
    data: params,
  })
}
// 删除会议
export async function remove (params) {
  return request({
    url: `${conferences}?id=${params.id}`,
    method: 'delete',
  })
}
// 更新会议
export async function update (params) {
  return request({
    url: conferences,
    method: 'patch',
    data: params,
  })
}
// 创建会议编号
export async function createConfNo (params) {
  return request({
    url: `${conferences}/createConfNo`,
    method: 'get',
    data: params,
  })
}

export async function queryPerson (params) {
  return request({
    url: `${conferences}/${params.confId}/persons`,
    method: 'get',
    data: params,
  })
}

export async function createPerson (params) {
  return request({
    url: `${conferences}/${params.confId}/persons`,
    method: 'post',
    data: params,
  })
}

export async function updatePerson (params) {
  return request({
    url: `${conferences}/${params.confId}/persons`,
    method: 'patch',
    data: params,
  })
}

export async function removePerson (params) {
  return request({
    url: `${conferences}/${params.confId}/persons?id=${params.id}&type=${params.type}`,
    method: 'delete',
    data: params,
  })
}

export async function uploadPerson (params) {
  return request({
    url: `${conferences}/${params.confId}/persons/upload`,
    method: 'post',
    data: params,
  })
}

export async function queryCheckin (params) {
  return request({
    url: `${conferences}/${params.confId}/checkins`,
    method: 'get',
    data: params,
  })
}

export async function createCheckin (params) {
  return request({
    url: `${conferences}/${params.confId}/checkins`,
    method: 'post',
    data: params,
  })
}

export async function updateCheckin (params) {
  return request({
    url: `${conferences}/${params.confId}/checkins`,
    method: 'patch',
    data: params,
  })
}

export async function removeCheckin (params) {
  return request({
    url: `${conferences}/${params.confId}/checkins?id=${params.id}`,
    method: 'delete',
    data: params,
  })
}

export async function queryEvents (params) {
  return request({
    url: `${conferences}/events`,
    method: 'get',
    data: params,
  })
}


export async function queryPlace (params) {
  return request({
    url: `${conferences}/places`,
    method: 'get',
    data: params,
  })
}

export async function createPlace (params) {
  return request({
    url: `${conferences}/places`,
    method: 'post',
    data: params,
  })
}

export async function removePlace (params) {
  return request({
    url: `${conferences}/places?id=${params.id}`,
    method: 'delete',
    data: params,
  })
}

export async function updatePlace (params) {
  return request({
    url: `${conferences}/places`,
    method: 'patch',
    data: params,
  })
}

export async function queryPlaceRoom (params) {
  return request({
    url: `${conferences}/places/${params.placeId}/rooms`,
    method: 'get',
    data: params,
  })
}

export async function createPlaceRoom (params) {
  return request({
    url: `${conferences}/places/${params.placeId}/rooms`,
    method: 'post',
    data: params,
  })
}

export async function removePlaceRoom (params) {
  return request({
    url: `${conferences}/places/${params.placeId}/rooms?id=${params.id}`,
    method: 'delete',
    data: params,
  })
}

export async function updatePlaceRoom (params) {
  return request({
    url: `${conferences}/places/${params.placeId}/rooms`,
    method: 'patch',
    data: params,
  })
}

export async function queryPlacesRooms (params) {
  return request({
    url: `${conferences}/places/rooms`,
    method: 'get',
    data: params,
  })
}

export async function queryCourse (params) {
  return request({
    url: `${conferences}/${params.confId}/courses`,
    method: 'get',
    data: params,
  })
}

export async function createCourse (params) {
  return request({
    url: `${conferences}/${params.confId}/courses`,
    method: 'post',
    data: params,
  })
}

export async function removeCourse (params) {
  return request({
    url: `${conferences}/${params.confId}/courses?id=${params.id}`,
    method: 'delete',
    data: params,
  })
}

export async function updateCourse (params) {
  return request({
    url: `${conferences}/${params.confId}/courses`,
    method: 'patch',
    data: params,
  })
}


// 证件增
export async function createIdentity (params) {
  return request({
    url: `${conferences}/${params.confIdNumber}/identity`,
    method: 'post',
    data: params,
  })
}

// 证件删
export async function deleteIdentity (params) {
  return request({
    url: `${conferences}/${params.confId}/identity?id=${params.id}`,
    method: 'delete',
    data: params,
  })
}


// 证件改
export async function updateIdentity (params) {
  return request({
    url: `${conferences}/${params.confIdNumber}/identity`,
    method: 'patch',
    data: params,
  })
}

// 证件查
export async function queryIdentity (params) {
  return request({
    url: `${conferences}/${params.confId}/identity`,
    method: 'get',
    data: params,
  })
}

export async function queryConfRooms (params) {
  return request({
    url: `${conferences}/${params.confId}/rooms`,
    method: 'get',
    data: params,
  })
}

export async function queryPersonByFilter (params) {
  return request({
    url: `${conferences}/personAutoComplete`,
    method: 'get',
    data: params,
  })
}
