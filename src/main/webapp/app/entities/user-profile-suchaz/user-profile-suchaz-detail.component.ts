import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { UserProfileSuchaz } from './user-profile-suchaz.model';
import { UserProfileSuchazService } from './user-profile-suchaz.service';

@Component({
    selector: 'jhi-user-profile-suchaz-detail',
    templateUrl: './user-profile-suchaz-detail.component.html'
})
export class UserProfileSuchazDetailComponent implements OnInit, OnDestroy {

    userProfile: UserProfileSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private userProfileService: UserProfileSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInUserProfiles();
    }

    load(id) {
        this.userProfileService.find(id)
            .subscribe((userProfileResponse: HttpResponse<UserProfileSuchaz>) => {
                this.userProfile = userProfileResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInUserProfiles() {
        this.eventSubscriber = this.eventManager.subscribe(
            'userProfileListModification',
            (response) => this.load(this.userProfile.id)
        );
    }
}
