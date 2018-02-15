import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { HobbySuchaz } from './hobby-suchaz.model';
import { HobbySuchazService } from './hobby-suchaz.service';

@Component({
    selector: 'jhi-hobby-suchaz-detail',
    templateUrl: './hobby-suchaz-detail.component.html'
})
export class HobbySuchazDetailComponent implements OnInit, OnDestroy {

    hobby: HobbySuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private hobbyService: HobbySuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInHobbies();
    }

    load(id) {
        this.hobbyService.find(id)
            .subscribe((hobbyResponse: HttpResponse<HobbySuchaz>) => {
                this.hobby = hobbyResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInHobbies() {
        this.eventSubscriber = this.eventManager.subscribe(
            'hobbyListModification',
            (response) => this.load(this.hobby.id)
        );
    }
}
