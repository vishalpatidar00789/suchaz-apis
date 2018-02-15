import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { SuchAzUserSuchaz } from './such-az-user-suchaz.model';
import { SuchAzUserSuchazService } from './such-az-user-suchaz.service';

@Component({
    selector: 'jhi-such-az-user-suchaz-detail',
    templateUrl: './such-az-user-suchaz-detail.component.html'
})
export class SuchAzUserSuchazDetailComponent implements OnInit, OnDestroy {

    suchAzUser: SuchAzUserSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private suchAzUserService: SuchAzUserSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSuchAzUsers();
    }

    load(id) {
        this.suchAzUserService.find(id)
            .subscribe((suchAzUserResponse: HttpResponse<SuchAzUserSuchaz>) => {
                this.suchAzUser = suchAzUserResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSuchAzUsers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'suchAzUserListModification',
            (response) => this.load(this.suchAzUser.id)
        );
    }
}
