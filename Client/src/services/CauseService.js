import axios from 'axios'
export default {
  getAllCauses: async function () {
    return axios
      .get('http://localhost:8085/cause/')
      .then(response => (this.causes = response.data))
  },
  getCauseById (causeId) {
    return axios
      .get('http://localhost:8085/cause/' + causeId)
      .then(response => (this.causes = response.data))
  },
  creatingCause: async function (userJwt, causeData) {
    const URL = 'http://localhost:8085/cause/'
    return axios({
      method: 'post',
      url: URL,
      headers: {'authorization': userJwt},
      data: causeData
    })
  },
  updatingCause: async function (userJwt, causeData) {
    const URL = 'http://localhost:8085/cause/'
    return axios({
      method: 'put',
      url: URL,
      headers: {'authorization': userJwt},
      data: causeData
    })
  },
  getAllCategories: async function () {
    return axios
      .get('http://localhost:8085/category/')
      .then(response => (this.category = response.data))
  },
  postDonation: async function (userJwt, config) {
    const URL = 'http://localhost:8085/donation/'
    return axios({
      method: 'post',
      url: URL,
      headers: {'authorization': userJwt},
      data: config
    })
  },
  searchCauseByNameAndId: async function (causeName, categoryId) {
    console.log('searchCauseByNameAndId ' + causeName + categoryId)
    var catId = 0
    switch (categoryId) {
      case 'Education Help' : catId = 3
        break
      case 'Medical Cause' : catId = 2
        break
      case 'Natural Disaster' : catId = 1
        break
    }
    console.log('cat name ' + categoryId + 'cat id ' + catId)
    const URL = 'http://localhost:8085/cause/searchCause'

    return axios({
      method: 'get',
      url: URL,
      params: { 'categoryId': catId, 'searchString': causeName }
    })
  },
  searchCauseById: async function (categoryId) {
    var catId = 0
    switch (categoryId) {
      case 'Education Help' : catId = 3
        break
      case 'Medical Cause' : catId = 2
        break
      case 'Natural Disaster' : catId = 1
        break
    }
    console.log('searchCauseById cat name ' + categoryId + ' cat id ' + catId)
    const URL = 'http://localhost:8085/cause/searchCause'

    if (catId === 0) {
      return axios({
        method: 'get',
        url: URL,
        params: { 'categoryId': catId, 'searchString': '' }
      })
    } else {
      return axios({
        method: 'get',
        url: URL,
        params: { 'categoryId': catId }
      })
    }
  },
  searchCauseByName: async function (causeName) {
    console.log('searchCauseByName causename and categ ' + causeName)
    const URL = 'http://localhost:8085/cause/searchCause'

    return axios({
      method: 'get',
      url: URL,
      params: { 'searchString': causeName }
    })
  }
}
