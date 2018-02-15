/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { RelationshipSuchazComponent } from '../../../../../../main/webapp/app/entities/relationship-suchaz/relationship-suchaz.component';
import { RelationshipSuchazService } from '../../../../../../main/webapp/app/entities/relationship-suchaz/relationship-suchaz.service';
import { RelationshipSuchaz } from '../../../../../../main/webapp/app/entities/relationship-suchaz/relationship-suchaz.model';

describe('Component Tests', () => {

    describe('RelationshipSuchaz Management Component', () => {
        let comp: RelationshipSuchazComponent;
        let fixture: ComponentFixture<RelationshipSuchazComponent>;
        let service: RelationshipSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [RelationshipSuchazComponent],
                providers: [
                    RelationshipSuchazService
                ]
            })
            .overrideTemplate(RelationshipSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RelationshipSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RelationshipSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new RelationshipSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.relationships[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
