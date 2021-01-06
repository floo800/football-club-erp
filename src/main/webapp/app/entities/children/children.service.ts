import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IChildren } from 'app/shared/model/children.model';

type EntityResponseType = HttpResponse<IChildren>;
type EntityArrayResponseType = HttpResponse<IChildren[]>;

@Injectable({ providedIn: 'root' })
export class ChildrenService {
  public resourceUrl = SERVER_API_URL + 'api/children';

  constructor(protected http: HttpClient) {}

  create(children: IChildren): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(children);
    return this.http
      .post<IChildren>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(children: IChildren): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(children);
    return this.http
      .put<IChildren>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IChildren>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IChildren[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(children: IChildren): IChildren {
    const copy: IChildren = Object.assign({}, children, {
      birthDate: children.birthDate && children.birthDate.isValid() ? children.birthDate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.birthDate = res.body.birthDate ? moment(res.body.birthDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((children: IChildren) => {
        children.birthDate = children.birthDate ? moment(children.birthDate) : undefined;
      });
    }
    return res;
  }
}
