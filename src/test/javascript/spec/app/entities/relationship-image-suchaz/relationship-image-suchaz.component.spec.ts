/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { RelationshipImageSuchazComponent } from '../../../../../../main/webapp/app/entities/relationship-image-suchaz/relationship-image-suchaz.component';
import { RelationshipImageSuchazService } from '../../../../../../main/webapp/app/entities/relationship-image-suchaz/relationship-image-suchaz.service';
import { RelationshipImageSuchaz } from '../../../../../../main/webapp/app/entities/relationship-image-suchaz/relationship-image-suchaz.model';

describe('Component Tests', () => {

    describe('RelationshipImageSuchaz Management Component', () => {
        let comp: RelationshipImageSuchazComponent;
        let fixture: ComponentFixture<RelationshipImageSuchazComponent>;
        let service: RelationshipImageSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [RelationshipImageSuchazComponent],
                providers: [
                    RelationshipImageSuchazService
                ]
            })
            .overrideTemplate(RelationshipImageSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RelationshipImageSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RelationshipImageSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new RelationshipImageSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.relationshipImages[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
