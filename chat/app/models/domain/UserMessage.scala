package models.domain


import models.store.Message


case class UserMessage(message: Message, isRead: Boolean)
