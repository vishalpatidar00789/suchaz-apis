import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { HobbySuchaz } from './hobby-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<HobbySuchaz>;

@Injectable()
export class HobbySuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/hobbies';

    constructor(private http: HttpClient) { }

    create(hobby: HobbySuchaz): Observable<EntityResponseType> {
        const copy = this.convert(hobby);
        return this.http.post<HobbySuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(hobby: HobbySuchaz): Observable<EntityResponseType> {
        const copy = this.convert(hobby);
        return this.http.put<HobbySuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<HobbySuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<HobbySuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<HobbySuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<HobbySuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: HobbySuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<HobbySuchaz[]>): HttpResponse<HobbySuchaz[]> {
        const jsonResponse: HobbySuchaz[] = res.body;
        const body: HobbySuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to HobbySuchaz.
     */
    private convertItemFromServer(hobby: HobbySuchaz): HobbySuchaz {
        const copy: HobbySuchaz = Object.assign({}, hobby);
        return copy;
    }

    /**
     * Convert a HobbySuchaz to a JSON which can be sent to the server.
     */
    private convert(hobby: HobbySuchaz): HobbySuchaz {
        const copy: HobbySuchaz = Object.assign({}, hobby);
        return copy;
    }
}
