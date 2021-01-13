import { Moment } from 'moment';

export interface IEvent {
  id?: number;
  name?: string;
  startDate?: Moment;
  endDate?: Moment;
  description?: string;
  streetAndNumber?: string;
  cityAndZipCode?: string;
  country?: string;
  imageContentType?: string;
  image?: any;
}

export class Event implements IEvent {
  constructor(
    public id?: number,
    public name?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public description?: string,
    public streetAndNumber?: string,
    public cityAndZipCode?: string,
    public country?: string,
    public imageContentType?: string,
    public image?: any
  ) {}
}
