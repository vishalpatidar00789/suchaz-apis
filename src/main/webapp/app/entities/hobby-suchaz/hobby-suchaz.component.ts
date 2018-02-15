import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { HobbySuchaz } from './hobby-suchaz.model';
import { HobbySuchazService } from './hobby-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-hobby-suchaz',
    templateUrl: './hobby-suchaz.component.html'
})
export class HobbySuchazComponent implements OnInit, OnDestroy {
hobbies: HobbySuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private hobbyService: HobbySuchazService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.hobbyService.query().subscribe(
            (res: HttpResponse<HobbySuchaz[]>) => {
                this.hobbies = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInHobbies();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: HobbySuchaz) {
        return item.id;
    }
    registerChangeInHobbies() {
        this.eventSubscriber = this.eventManager.subscribe('hobbyListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
