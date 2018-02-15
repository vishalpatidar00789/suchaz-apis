import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { HobbyImageSuchaz } from './hobby-image-suchaz.model';
import { HobbyImageSuchazService } from './hobby-image-suchaz.service';

@Component({
    selector: 'jhi-hobby-image-suchaz-detail',
    templateUrl: './hobby-image-suchaz-detail.component.html'
})
export class HobbyImageSuchazDetailComponent implements OnInit, OnDestroy {

    hobbyImage: HobbyImageSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private hobbyImageService: HobbyImageSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInHobbyImages();
    }

    load(id) {
        this.hobbyImageService.find(id)
            .subscribe((hobbyImageResponse: HttpResponse<HobbyImageSuchaz>) => {
                this.hobbyImage = hobbyImageResponse.body;
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

    registerChangeInHobbyImages() {
        this.eventSubscriber = this.eventManager.subscribe(
            'hobbyImageListModification',
            (response) => this.load(this.hobbyImage.id)
        );
    }
}
