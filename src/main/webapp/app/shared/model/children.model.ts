import { Moment } from 'moment';

export interface IChildren {
  id?: number;
  name?: string;
  firstName?: string;
  birthDate?: Moment;
  birthCountry?: string;
  birthCity?: string;
  photoContentType?: string;
  photo?: any;
  parentKey?: string;
  teamId?: number;
}

export class Children implements IChildren {
  constructor(
    public id?: number,
    public name?: string,
    public firstName?: string,
    public birthDate?: Moment,
    public birthCountry?: string,
    public birthCity?: string,
    public photoContentType?: string,
    public photo?: any,
    public parentKey?: string,
    public teamId?: number
  ) {}
}
