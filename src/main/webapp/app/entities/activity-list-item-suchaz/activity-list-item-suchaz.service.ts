import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { ActivityListItemSuchaz } from './activity-list-item-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<ActivityListItemSuchaz>;

@Injectable()
export class ActivityListItemSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/activity-list-items';

    constructor(private http: HttpClient) { }

    create(activityListItem: ActivityListItemSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(activityListItem);
        return this.http.post<ActivityListItemSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(activityListItem: ActivityListItemSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(activityListItem);
        return this.http.put<ActivityListItemSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ActivityListItemSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<ActivityListItemSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<ActivityListItemSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ActivityListItemSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ActivityListItemSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ActivityListItemSuchaz[]>): HttpResponse<ActivityListItemSuchaz[]> {
        const jsonResponse: ActivityListItemSuchaz[] = res.body;
        const body: ActivityListItemSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to ActivityListItemSuchaz.
     */
    private convertItemFromServer(activityListItem: ActivityListItemSuchaz): ActivityListItemSuchaz {
        const copy: ActivityListItemSuchaz = Object.assign({}, activityListItem);
        return copy;
    }

    /**
     * Convert a ActivityListItemSuchaz to a JSON which can be sent to the server.
     */
    private convert(activityListItem: ActivityListItemSuchaz): ActivityListItemSuchaz {
        const copy: ActivityListItemSuchaz = Object.assign({}, activityListItem);
        return copy;
    }
}
